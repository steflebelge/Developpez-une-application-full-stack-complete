import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable, Subject} from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ApiService {
  private baseUrl = 'http://localhost:8080/api';

  // Subject pour notifier la suppression d'abonnement
  private abonnementRemovedSource = new Subject<number>();
  abonnementRemoved$ = this.abonnementRemovedSource.asObservable();

  private abonnementAddedSource = new Subject<number>();
  abonnementAdded$ = this.abonnementAddedSource.asObservable();

  constructor(private http: HttpClient) {}

  get<T>(endpoint: string): Observable<T> {
    return this.http.get<T>(`${this.baseUrl}/${endpoint}`);
  }

  post<T>(endpoint: string, body: any): Observable<T> {
    return this.http.post<T>(`${this.baseUrl}/${endpoint}`, body);
  }

  put<T>(endpoint: string, body: any): Observable<T> {
    return this.http.put<T>(`${this.baseUrl}/${endpoint}`, body);
  }

  delete<T>(endpoint: string): Observable<T> {
    return this.http.delete<T>(`${this.baseUrl}/${endpoint}`);
  }

  notifyAbonnementRemoved(themeId: number) {
    this.abonnementRemovedSource.next(themeId);
  }

  notifyAbonnementAdded(idTheme: number) {
    this.abonnementAddedSource.next(idTheme);
  }
}
