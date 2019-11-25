import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
//components
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './login/register.component';
//Rutas:
import { AppRoutes } from './app.routes';
//modulos:
import {PagesModule} from './pages/pages.module';
//form:
import { ReactiveFormsModule, FormControl, FormsModule } from '@angular/forms';
//servicios modulo:
import {ServiceModule} from './services/service.module';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent    
  ],
  imports: [
    BrowserModule,
    AppRoutes,
    PagesModule,
    FormsModule,
    ServiceModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
