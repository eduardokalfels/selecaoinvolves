import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AlertaCardListComponent } from './alerta/alerta-card-list/alerta-card-list.component';

const routes: Routes = [
  { 
    path: '', component: AlertaCardListComponent 
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
