import {Component, Input, OnInit} from '@angular/core';
import {Theme} from "../../models/theme.model";
import {ApiService} from "../../services/api.service";

@Component({
  selector: 'app-theme',
  templateUrl: './theme.component.html',
  styleUrls: ['./theme.component.scss']
})
export class ThemeComponent {
  @Input() theme!: Theme;
  @Input() unfollowLink: boolean = false;
  error: string | null = null;

  constructor(
    private apiService: ApiService,
  ) {
  }

  unsubscribe(): void {
    this.apiService.delete('abonnement/' + this.theme.idTheme).subscribe({
      next: () => {
        this.apiService.notifyAbonnementRemoved(this.theme.idTheme);
      },
      error: (err) => {
        this.error = err.error?.message || 'Erreur lors de la mise à jour';
      }
    });
  }

  subscribe(): void {
    this.apiService.post('abonnement/' + this.theme.idTheme, {}).subscribe({
      next: () => {
        this.theme.isUserAbonner = true;
      },
      error: (err) => {
        this.error = err.error?.message || 'Erreur lors de la mise à jour';
      }
    });
  }

}
