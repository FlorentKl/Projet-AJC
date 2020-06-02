import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-form-ingredient-recette',
  templateUrl: './form-ingredient-recette.component.html',
  styleUrls: ['./form-ingredient-recette.component.css']
})
export class FormIngredientRecetteComponent implements OnInit {

  private _nbIngredients: number = 0;
  private _ingredients: Array<number> = new Array();

  constructor() { }

  ngOnInit(): void {
    this.nbIngredients = 0;
    this.ingredients = [0];
  }

  public ajouterEtape(){
    this.nbIngredients++;
    this.ingredients.push(this.nbIngredients);
    console.log(this.ingredients);
  }

  public supprimerEtape(){
    if (this.nbIngredients > 0) {
      this.nbIngredients--;
      this.ingredients.pop();
    }
    console.log(this.ingredients);
  }


  get nbIngredients(): number {
    return this._nbIngredients;
  }

  set nbIngredients(value: number) {
    this._nbIngredients = value;
  }

  get ingredients(): Array<number> {
    return this._ingredients;
  }

  set ingredients(value: Array<number>) {
    this._ingredients = value;
  }
}
