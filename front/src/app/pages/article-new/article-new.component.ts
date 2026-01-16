import { Component, OnInit } from '@angular/core';
import {Theme} from "../../models/theme.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ApiService} from "../../services/api.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-article-new',
  templateUrl: './article-new.component.html',
  styleUrls: ['./article-new.component.scss']
})
export class ArticleNewComponent implements OnInit {
  form!: FormGroup;
  themes: Theme[] = [];
  error: string = '';

  constructor(private fb: FormBuilder, private location: Location, private apiService: ApiService) {}

  ngOnInit(): void {
    // 1️⃣ Créer le formulaire
    this.form = this.fb.group({
      themeId: [null, Validators.required],
      title: ['', [Validators.required, Validators.maxLength(100)]],
      content: ['', [Validators.required, Validators.minLength(10)]],
    });

    this.loadThemes();
  }

  loadThemes(): void {
    //récupérer les thèmes
    this.apiService.get<Theme[]>('themes/getAll').subscribe({
      next: (res) => this.themes = res,
      error: () => this.error = 'Erreur lors de la récupération des themes'
    });
  }

  submit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const payload = this.form.value;
    this.apiService.post('articles/create', payload).subscribe({
      next: () => {
        console.log('Post créé !');
        this.form.reset();
      },
      error: (err) => {
        this.error = err.error?.message || 'Erreur lors de la création du post';
      }
    });
  }

  goBack(): void {
    this.location.back();
  }
}
