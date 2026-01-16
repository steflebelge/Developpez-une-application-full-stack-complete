import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './pages/home/home.component';
import {LoginComponent} from "./pages/login/login.component";
import {GuestGuard} from "./guards/guest.guard";
import {SignupComponent} from "./pages/signup/signup.component";
import {AuthGuard} from "./guards/auth.guard";
import {NotFoundComponent} from "./pages/not-found/not-found.component";
import {ArticlesComponent} from "./pages/articles/articles.component";
import {AccountComponent} from "./pages/account/account.component";
import {ThemesComponent} from "./pages/themes/themes.component";
import {ArticleNewComponent} from "./pages/article-new/article-new.component";
import {ArticleDetailsComponent} from "./pages/article-details/article-details.component";

const routes: Routes = [

  // redirection racine
  { path: '', redirectTo: 'pub', pathMatch: 'full' },

  // routes publiques
  {
    path: 'pub',
    canActivate: [GuestGuard],
    children: [
      {path: '', component: HomeComponent},
      {path: 'login', component: LoginComponent},
      {path: 'signup', component: SignupComponent},
    ]
  },

  // routes privées
  {
    path: 'app',
    canActivate: [AuthGuard],
    children: [
      { path: 'articles', component: ArticlesComponent },
      { path: 'articles/details/:idArticle', component: ArticleDetailsComponent },
      { path: 'articles/new', component:  ArticleNewComponent},
      { path: 'themes', component: ThemesComponent },
      { path: 'account', component: AccountComponent }
    ]
  },

  // page 404
  { path: '**', component: NotFoundComponent }
];


@NgModule({
  imports: [
    RouterModule.forRoot(routes, { onSameUrlNavigation: 'reload' })
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {}
