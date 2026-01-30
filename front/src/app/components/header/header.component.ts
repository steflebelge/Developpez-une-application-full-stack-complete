import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  protected navBarClass: string = "hidden";

  constructor(public auth: AuthService, private router: Router) {
  }

  logout(): void {
    this.auth.logout();
    this.router.navigate(['/pub/login']);
  }

  toggleNavBar() {
    this.navBarClass = this.navBarClass === "hidden" ? "visible" : "hidden";
  }
}
