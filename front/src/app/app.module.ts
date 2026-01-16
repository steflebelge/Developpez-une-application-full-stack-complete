import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { SignupComponent } from './pages/signup/signup.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AuthInterceptor} from "./services/auth.interceptor";
import { NotFoundComponent } from './pages/not-found/not-found.component';
import { ArticlesComponent } from './pages/articles/articles.component';
import { HeaderComponent } from './components/header/header.component';
import {ReactiveFormsModule} from "@angular/forms";
import {MatIconModule} from "@angular/material/icon";
import {AccountComponent} from "./pages/account/account.component";
import { ThemeComponent } from './components/theme/theme.component';
import {ThemesComponent} from "./pages/themes/themes.component";
import {ArticleDetailsComponent} from "./pages/article-details/article-details.component";
import {ArticleNewComponent} from "./pages/article-new/article-new.component";

@NgModule({
  declarations: [AppComponent,
    HomeComponent,
    LoginComponent,
    SignupComponent,
    NotFoundComponent,
    ArticlesComponent,
    HeaderComponent,
    AccountComponent,
    ThemesComponent,
    ThemeComponent,
    ArticleDetailsComponent,
    ArticleNewComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatIconModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
