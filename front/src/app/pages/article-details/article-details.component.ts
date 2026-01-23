import { Component, OnInit } from '@angular/core';
import {Article} from "../../models/article.model";
import {ActivatedRoute, Router} from "@angular/router";
import {ApiService} from "../../services/api.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Commentaire} from "../../models/commentaire.model";

@Component({
  selector: 'app-article-details',
  templateUrl: './article-details.component.html',
  styleUrls: ['./article-details.component.scss']
})
export class ArticleDetailsComponent implements OnInit {
  idArticle!: number;
  form!: FormGroup;
  error: string = "";
  success: string = "";
  newComment: string = "";
  article?: Article;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private apiService: ApiService,
    ) { }


  ngOnInit(): void {

      this.idArticle = Number(this.route.snapshot.paramMap.get('idArticle'));

      if(!this.idArticle){
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
        this.error = 'Erreur lors de la récupération des articles';
      }
    });
  }

  submit(): void {
    console.log(this.newComment);

    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const payload = this.form.value;
    this.apiService.post('commentaires/', payload).subscribe({
      next: (value) => {
        console.log("commentaire créée : ", value)
        this.success = 'Commentaire ajouté !';
        this.form.reset();
      },
      error: (err) => {
        this.error = err.error?.message || 'Erreur lors de la création du post';
      }
    });
  }
}
