import {Routes} from '@angular/router';
import {IndexComponent} from './components/index/index.component';
import {RecipeCardComponent} from './components/recipe-card/recipe-card.component';
import {MilestonesComponent} from './components/milestones/milestones.component';
import {ImagesComponent} from './images/images.component';
import {BoissonComponent} from './components/boisson/boisson.component';
import {EntreeComponent} from './components/entree/entree.component';
import {PlatComponent} from './components/plat/plat.component';
import {DessertComponent} from './components/dessert/dessert.component';

export const routes: Routes = [
  {path: 'index', component: IndexComponent},
  {path: '', redirectTo: 'index', pathMatch: 'full'},
  {path: 'images', component: ImagesComponent},
  {path: 'recettes/:id', component: RecipeCardComponent},
  {path: 'boissons', component: BoissonComponent},
  {path: 'entrees', component: EntreeComponent},
  {path: 'plats', component: PlatComponent},
  {path: 'desserts', component: DessertComponent}
];
