import { Component, OnInit } from '@angular/core';
import {Recette} from '../../model/recette';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {RecetteService} from '../../services/recette.service';
import {ActivatedRoute, Router} from '@angular/router';
import {EtapeRecette} from '../../model/etape-recette';
import {Ingredient} from '../../model/ingredient';

@Component({
  selector: 'app-form-recette',
  templateUrl: './form-recette.component.html',
  styleUrls: ['./form-recette.component.css']
})
export class FormRecetteComponent implements OnInit {

  // Form control
  private _recette: Recette = new Recette();
  private _formRecette: FormGroup;
  private _nomCtrl: FormControl;
  private _tempsCtrl: FormControl;
  private _nbPersonneCtrl: FormControl;
  private _difficulteCtrl: FormControl;
  private _typeCtrl: FormControl;
  private _coutCtrl: FormControl;


  // Etapes Recettes
  private _nbEtapes = 0;
  private _etapes: Array<number> = new Array();
  private _etapesRecette: Array<EtapeRecette> = new Array<EtapeRecette>();

  // Ingrédients recettes
  private _nbIngredients = 0;
  private _ingredients: Array<number> = new Array();
  private _ingredientsArray: Array<Ingredient> = new Array<Ingredient>();




  constructor(private fb: FormBuilder, private activatedRoute: ActivatedRoute, private recetteService: RecetteService, private router: Router) {
    this.formRecette = fb.group({
      nom: this.nomCtrl, nbPersonne: this.nbPersonneCtrl, temps: this.tempsCtrl, difficulte: this.difficulteCtrl, type: this.typeCtrl, cout: this.coutCtrl
    });
  }

  ngOnInit(): void {
    this._nbEtapes = 0;
    this._etapes = [0];
    this.formRecette.addControl('etapeRecette' + this.nbEtapes, this.fb.control(''));
    this.etapesRecette.push(new EtapeRecette('', this.nbEtapes));

    this.nbIngredients = 0;
    this.ingredients = [0];
    this.formRecette.addControl('ingredientNom' + this.nbIngredients, this.fb.control(''));
    this.formRecette.addControl('ingredientQuantite' + this.nbIngredients, this.fb.control(''));
    this.formRecette.addControl('ingredientUnite' + this.nbIngredients, this.fb.control(''));
    this.ingredientsArray.push(new Ingredient());
  }

  public save(){

    // récupérer toutes les étapes et injecter l'array dans la recette
    for (const etape of this.etapesRecette) {
      const et = 'etapeRecette' + etape.numEtape;
      etape.texte = this.formRecette.get(et).value;
    }
    this.recette.etapes = this.etapesRecette;

    // récupérer les ingrédients et injecter dans l'array de la recette
    let i = 0;
    for (const ingredient of this.ingredientsArray) {
      const ingNom = 'ingredientNom' + i;
      const ingQte = 'ingredientQuantite' + i;
      const ingUnite = 'ingredientUnite' + i;
      ingredient.nom = this.formRecette.get(ingNom).value;
      ingredient.quantite = this.formRecette.get(ingQte).value;
      ingredient.unite = this.formRecette.get(ingUnite).value;
      i++;
    }
    this.recette.ingredients = this.ingredientsArray;

    //console.log(this.recette);

    this.recetteService.create(this.recette).subscribe(res => {
      //TODO - redirigier vers la page de recette plutot que l'index
      this.router.navigate(['index']);
    });
  }

  public ajouterEtape(){
    this.nbEtapes++;
    this.etapesRecette.push(new EtapeRecette('', this.nbEtapes));
    this.formRecette.addControl('etapeRecette' + this.nbEtapes, this.fb.control(''));
    this.etapes.push(this.nbEtapes);
    // console.log(this.etapes);
    console.log(this.etapesRecette);
  }

  public supprimerEtape(){
    if (this.nbEtapes > 0) {
      this.etapesRecette.pop();
      this.formRecette.removeControl('etapeRecette' + this.nbEtapes);
      this.nbEtapes--;
      this.etapes.pop();
    }
    console.log(this.etapes);
  }

  public ajouterIngredient(){
    this.nbIngredients++;
    this.ingredientsArray.push(new Ingredient(''));
    this.formRecette.addControl('ingredientNom' + this.nbIngredients, this.fb.control(''));
    this.formRecette.addControl('ingredientQuantite' + this.nbIngredients, this.fb.control(''));
    this.formRecette.addControl('ingredientUnite' + this.nbIngredients, this.fb.control(''));
    this.ingredients.push(this.nbIngredients);
    // console.log(this.ingredients);
    console.log(this.ingredientsArray);
  }

  public supprimerIngredient(){
    if (this.nbIngredients > 0) {
      this.nbIngredients--;
      this.ingredients.pop();
    }
    console.log(this.ingredients);
  }

  public changement(){
    for (const etape of this.etapesRecette) {
      const et = 'etapeRecette' + etape.numEtape;
      // console.log(this.formRecette.get(et).value);
      etape.texte = this.formRecette.get(et).value;
      // console.log(etape);
    }
    // console.log(this.etapesRecette);
    let i = 0;
    for (const ingredient of this.ingredientsArray) {
      const ingNom = 'ingredientNom' + i;
      const ingQte = 'ingredientQuantite' + i;
      const ingUnite = 'ingredientUnite' + i;
      ingredient.nom = this.formRecette.get(ingNom).value;
      ingredient.quantite = this.formRecette.get(ingQte).value;
      ingredient.unite = this.formRecette.get(ingUnite).value;
      i++;
    }
    console.log(this.ingredientsArray);

  }

  // Getters and setters
  get recette(): Recette {
    return this._recette;
  }

  set recette(value: Recette) {
    this._recette = value;
  }

  get formRecette(): FormGroup {
    return this._formRecette;
  }

  set formRecette(value: FormGroup) {
    this._formRecette = value;
  }

  get nomCtrl(): FormControl {
    return this._nomCtrl;
  }

  set nomCtrl(value: FormControl) {
    this._nomCtrl = value;
  }

  get tempsCtrl(): FormControl {
    return this._tempsCtrl;
  }

  set tempsCtrl(value: FormControl) {
    this._tempsCtrl = value;
  }

  get nbPersonneCtrl(): FormControl {
    return this._nbPersonneCtrl;
  }

  set nbPersonneCtrl(value: FormControl) {
    this._nbPersonneCtrl = value;
  }

  get difficulteCtrl(): FormControl {
    return this._difficulteCtrl;
  }

  set difficulteCtrl(value: FormControl) {
    this._difficulteCtrl = value;
  }

  get typeCtrl(): FormControl {
    return this._typeCtrl;
  }

  set typeCtrl(value: FormControl) {
    this._typeCtrl = value;
  }

  get coutCtrl(): FormControl {
    return this._coutCtrl;
  }

  set coutCtrl(value: FormControl) {
    this._coutCtrl = value;
  }

  get nbEtapes(): number {
    return this._nbEtapes;
  }

  set nbEtapes(value: number) {
    this._nbEtapes = value;
  }

  get etapes(): Array<number> {
    return this._etapes;
  }

  set etapes(value: Array<number>) {
    this._etapes = value;
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

  get etapesRecette(): Array<EtapeRecette> {
    return this._etapesRecette;
  }

  set etapesRecette(value: Array<EtapeRecette>) {
    this._etapesRecette = value;
  }

  get ingredientsArray(): Array<Ingredient> {
    return this._ingredientsArray;
  }

  set ingredientsArray(value: Array<Ingredient>) {
    this._ingredientsArray = value;
  }
}
