import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  login() {
    this.router.navigate(['/pub/login']);
  }

  signup() {
    this.router.navigate(['/pub/signup']);
  }
}
