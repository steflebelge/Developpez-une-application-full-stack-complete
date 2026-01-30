import {Injectable} from '@angular/core';
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import {AuthService} from './auth.service';
import {Router} from "@angular/router";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(
    private auth: AuthService,
    private router: Router,
  ) {
  }

  //Ajout du token d'identification dans les headers des requetes
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.auth.getToken();
    if (token) {
      const authReq = token
        ? req.clone({
          setHeaders: {
            Authorization: `Bearer ${token}`,
          },
        })
        : req;

      return next.handle(authReq).pipe(
        catchError((error: HttpErrorResponse): Observable<HttpEvent<any>> => {
          if (error.status === 401 || error.status === 403) {
            if (this.auth.getToken())
              this.auth.logout();
            this.router.navigate(['/pub/login']);
          }

          return throwError(() => error);
        })
      );
    }

    return next.handle(req);
  }
}
