import {Component, OnInit} from '@angular/core';
import {Article} from "../../models/article.model";
import {ActivatedRoute} from "@angular/router";
import {ApiService} from "../../services/api.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Location} from "@angular/common";

@Component({
  selector: 'app-article-details',
  templateUrl: './article-details.component.html',
  styleUrls: ['./article-details.component.scss']
})
export class ArticleDetailsComponent implements OnInit {
  idArticle!: number;
  form!: FormGroup;
  editForm: FormGroup;
  error: string = "";
  success: string = "";
  newComment: string = "";
  article?: Article;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private location: Location,
    private apiService: ApiService,
  ) {

    this.editForm = this.fb.group({
      content: [
        '',
        [
          Validators.required,
          Validators.minLength(1),
        ]
      ]
    });
  }


  ngOnInit(): void {

    this.idArticle = Number(this.route.snapshot.paramMap.get('idArticle'));

    if (!this.idArticle) {
      this.error = "Aucun article correspondant trouvé."
    } else {
      //chargement de l'Article
      this.loadArticle();
    }
  }

  loadArticle(): void {
    //récupérer l'article
    this.apiService.get<Article>('articles/' + this.idArticle).subscribe({
      next: (res: any) => {
        this.article = res;
        this.form = this.fb.group({
          idArticle: this.idArticle,
          content: ['', [Validators.required, Validators.minLength(1)]],
        });
      },
      error: (err) => {
        this.error = err.error?.message || 'Erreur lors de la récupération des articles';
      }
    });
  }

  submit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      this.error = 'Le contenu est trop court (min 1 caractère).';
      this.success = "";
      return;
    }

    const payload = this.form.value;
    this.apiService.post('commentaires/create', payload).subscribe({
      next: (value) => {
        this.success = 'Commentaire ajouté !';
        this.error = '';
        this.form.reset();
        this.loadArticle();
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
