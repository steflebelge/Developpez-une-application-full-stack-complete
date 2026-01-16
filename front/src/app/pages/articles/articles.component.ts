import { Component, OnInit } from '@angular/core';
import {Article} from "../../models/article.model";
import {ApiService} from "../../services/api.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.scss']
})
export class ArticlesComponent implements OnInit {

  articles: Article[] = [];
  error: string | null = null;

  constructor(private apiService: ApiService, private router: Router) { }

  ngOnInit(): void {
    //chargement des Articles
    this.loadArticles();
  }

  loadArticles(): void {
    //récupérer les articles
    this.apiService.get<Article[]>('articles/getAll').subscribe({
      next: (res) => this.articles = res,
      error: () => this.error = 'Erreur lors de la récupération des articles'
    });
  }

  details(idArticle: number){
    this.router.navigate(['/app/articles/details' + idArticle]);
  }

  createArticle(){
    this.router.navigate(['/app/articles/new']);
  }
}
