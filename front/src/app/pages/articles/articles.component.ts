import { Component, OnInit } from '@angular/core';
import {Article} from "../../models/article.model";
import {ApiService} from "../../services/api.service";
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.scss']
})
export class ArticlesComponent implements OnInit {

  articles: Article[] = [];
  error: string | null = null;
  sorted: string = "desc";

  constructor(
    private apiService: ApiService,
    private router: Router,
    private auth: AuthService
    ) { }

  ngOnInit(): void {
    //chargement des Articles
    this.loadArticles();
  }

  loadArticles(): void {
    //récupérer les articles
    this.apiService.get<Article[]>('articles/getAll').subscribe({
      next: (res) => this.articles = res,
      error: (err) => {
          this.error = 'Erreur lors de la récupération des articles';
      }
    });
  }

  details(article: Article) {
    this.router.navigate(['/app/articles/details/' + article.idArticle]);
  }

  createArticle(){
    this.router.navigate(['/app/articles/new']);
  }

  toggleSortArticles(){
    this.sorted = this.sorted === "desc" ? "asc" : "desc";

    this.articles.sort((a: Article, b: Article) => {
      // conversion en timestamp pour comparer
      const dateA = a.date ? new Date(a.date).getTime() : 0;
      const dateB = b.date ? new Date(b.date).getTime() : 0;

      if (this.sorted === "asc") {
        return dateA - dateB;
      } else {
        return dateB - dateA;
      }
    });
  }
}
