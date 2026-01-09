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

const routes: Routes = [
  // routes publiques
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent, canActivate: [GuestGuard] },
  { path: 'signup', component: SignupComponent, canActivate: [GuestGuard] },

  // routes privées
  {
    path: 'app',
    canActivate: [AuthGuard],
    children: [
      { path: 'articles', component: ArticlesComponent },
      { path: 'account', component: AccountComponent }
    ]
  },

  // page 404
  { path: '**', component: NotFoundComponent }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
