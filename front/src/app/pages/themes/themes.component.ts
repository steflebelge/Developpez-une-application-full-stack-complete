import { Component, OnInit } from '@angular/core';
import {Theme} from "../../models/theme.model";
import {Subscription} from "rxjs";
import {ApiService} from "../../services/api.service";

@Component({
  selector: 'app-articles',
  templateUrl: './themes.component.html',
  styleUrls: ['./themes.component.scss']
})
export class ThemesComponent implements OnInit {

  themes: Theme[] = [];
  private subscriptionRemoved!: Subscription;
  private subscriptionAdded!: Subscription;
  error: string | null = null;

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    //chargement des themes
    this.loadThemes();

    // S'abonner aux notifications des abonnements supprimés
    this.subscriptionRemoved = this.apiService.abonnementRemoved$.subscribe(themeId => {
      this.loadThemes();
    });

    // S'abonner aux notifications des abonnements ajoutés
    this.subscriptionAdded = this.apiService.abonnementAdded$.subscribe(themeId => {
      this.loadThemes();
    });
  }

  ngOnDestroy(): void {
    this.subscriptionRemoved.unsubscribe();
    this.subscriptionAdded.unsubscribe();
  }

  loadThemes(): void {
    //récupérer les thèmes
    this.apiService.get<Theme[]>('themes/getAll').subscribe({
      next: (res) => this.themes = res,
      error: () => this.error = 'Erreur lors de la récupération des themes'
    });
  }

}
