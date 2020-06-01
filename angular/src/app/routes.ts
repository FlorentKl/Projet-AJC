import {Routes} from '@angular/router';
import {IndexComponent} from './components/index/index.component';
import {RecipeComponent} from './components/recipe/recipe.component';
import {RecipeCardComponent} from './components/recipe-card/recipe-card.component';

export const routes: Routes = [
  {path: 'index', component: IndexComponent},
  {path: 'recette', component: RecipeComponent},
  {path: '', redirectTo: 'index', pathMatch: 'full'}

];
