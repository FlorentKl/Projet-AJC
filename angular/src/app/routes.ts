import {Routes} from '@angular/router';
import {RecipeComponent} from './components/recipe/recipe.component';
import {HomeComponent} from './components/home/home.component';

export const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'produits', component: RecipeComponent},
  {path: '', redirectTo: 'home', pathMatch: 'full'}
];
