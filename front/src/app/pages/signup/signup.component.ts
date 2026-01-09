import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ApiService } from '../../services/api.service';
import { Router } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {

  signupForm!: FormGroup;
  submitted = false;
  error?: string;

  constructor(
    private fb: FormBuilder,
    private location: Location,
    private apiService: ApiService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.signupForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  goBack(): void {
    this.location.back();
  }

  onSubmit(): void {
    this.submitted = true;

    if (this.signupForm.invalid) return;

    this.apiService.post('auth/signup', this.signupForm.value).subscribe({
      next: () => this.router.navigate(['/login']),
      error: () => this.error = 'Erreur lors de l’inscription'
    });
  }
}
