import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {Location} from '@angular/common';
import {ApiService} from "../../services/api.service";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;
  submitted = false;
  error?: string;

  constructor(
    private fb: FormBuilder,
    private location: Location,
    private apiService: ApiService,
    private router: Router,
    private authService: AuthService,
  ) {
  }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      login: ['azertyuiop', [Validators.required, Validators.minLength(3)]],
      password: ['azertyuiop', [Validators.required, Validators.minLength(6)]]
    });
  }

  goBack(): void {
    this.location.back();
  }

  onSubmit(): void {
    this.submitted = true;
    if (this.loginForm.invalid) return;

    this.apiService.post<{ token: string }>('auth/login', this.loginForm.value).subscribe({
      next: (res) => {
        this.authService.login(res?.token);
        this.router.navigate(['/app/articles']);
      },
      error: () => this.error = 'Erreur lors du login'
    });
  }
}
