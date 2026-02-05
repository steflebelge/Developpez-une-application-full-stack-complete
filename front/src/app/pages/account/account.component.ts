import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ApiService} from "../../services/api.service";
import {Router} from "@angular/router";
import {Theme} from '../../models/theme.model';
import {User} from "../../models/user.model";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit {

  editForm: FormGroup;
  submitted = false;
  error: string | null = null;
  success: string | null = null;
  themes: Theme[] = [];
  private subscription!: Subscription;

  constructor(
    private fb: FormBuilder,
    private apiService: ApiService,
  ) {
    this.editForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.minLength(8)]],
    });
  }

  ngOnInit(): void {
    //chargement des abonnements
    this.loadAbonnements();

    // S'abonner aux notifications des abonnements supprimés
    this.subscription = this.apiService.abonnementRemoved$.subscribe(themeId => {
      this.loadAbonnements();
    });

    //chargements des infos utilisateur
    this.loadInfosUser();
  }

  loadInfosUser(): void {
    //récupérer les infos utilisateur
    this.apiService.get<User>('auth/me').subscribe({
      next: (res) => {
        // Préremplir le formulaire avec les infos de l'utilisateur
        const user = res;
        if (user) {
          this.editForm.patchValue({
            name: user.name,
            email: user.email
          });
        }
      },
      error: (err) => {
        this.error = err.error?.message || 'Erreur lors du login';
        this.success = "";
      }
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  loadAbonnements() {
    //récupérer les thèmes utilisateurs
    this.apiService.get<Theme[]>('abonnement/').subscribe({
      next: (res) => this.themes = res,
      error: (err) => this.error = err.error?.message || 'Erreur lors du login'
    });
  }

  onSubmit() {
    this.submitted = true;
    if (this.editForm.invalid) return;

    const payload = this.editForm.value;

    this.apiService.put('user/edit', payload).subscribe({
      next: () => {
        // actualiser la page
        this.loadAbonnements();
        this.success = "Profil mis a jour";
        this.error = "";
      },
      error: (err) => {
        this.error = err.error?.message || 'Erreur lors de la mise à jour';
        this.success = "";
      }
    });
  }

}
