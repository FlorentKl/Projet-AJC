import { Component, OnInit } from '@angular/core';
import { Recette } from '../../model/recette';
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { RecetteService } from '../../services/recette.service';
import { ActivatedRoute, Router } from '@angular/router';
import { EtapeRecette } from '../../model/etape-recette';
import { Ingredient } from '../../model/ingredient';
import { HttpClient } from '@angular/common/http';
import { ImageModel } from 'src/app/model/image-model';
import { debounceTime, map } from 'rxjs/operators';

@Component({
  selector: 'app-form-recette',
  templateUrl: './form-recette.component.html',
  styleUrls: ['./form-recette.component.css'],
})
export class FormRecetteComponent implements OnInit {
  //Login
  login: any;
  //Image
  public selectedFile;
  public event1;
  imgURL: any;

  // Form control
  private _recette: Recette = new Recette();
  private _formRecette: FormGroup;
  private _nomCtrl: FormControl;
  private _tempsCtrl: FormControl;
  private _nbPersonneCtrl: FormControl;
  private _difficulteCtrl: FormControl;
  private _typeCtrl: FormControl;
  private _coutCtrl: FormControl;
  private _imageCtrl: FormControl;

  // Etapes Recettes
  private _nbEtapes = 0;
  private _etapes: Array<number> = new Array();
  private _etapesRecette: Array<EtapeRecette> = new Array<EtapeRecette>();

  // Ingrédients recettes
  private _nbIngredients = 0;
  private _ingredients: Array<number> = new Array();
  private _ingredientsArray: Array<Ingredient> = new Array<Ingredient>();

  constructor(
    private fb: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private recetteService: RecetteService,
    private router: Router,
    private httpClient: HttpClient
  ) {
    this.nomCtrl = fb.control('', Validators.required);
    this.typeCtrl = fb.control('', Validators.required);
    this.nbPersonneCtrl = fb.control('', [
      Validators.required,
      FormRecetteComponent.positive,
    ]);
    this.tempsCtrl = fb.control('', [
      Validators.required,
      FormRecetteComponent.positive,
    ]);
    this.difficulteCtrl = fb.control('', Validators.required);
    this.coutCtrl = fb.control('', Validators.required);
    this.imageCtrl = fb.control('', Validators.required);
    this.formRecette = fb.group({
      nom: this.nomCtrl,
      nbPersonne: this.nbPersonneCtrl,
      temps: this.tempsCtrl,
      difficulte: this.difficulteCtrl,
      type: this.typeCtrl,
      cout: this.coutCtrl,
      image: this.imageCtrl,
    });
  }

  ngOnInit(): void {
    this.login = sessionStorage.getItem('login');

    this._nbEtapes = 0;
    this._etapes = [0];
    this.formRecette.addControl(
      'etapeRecette' + this.nbEtapes,
      this.fb.control('', Validators.required)
    );
    this.etapesRecette.push(new EtapeRecette('', this.nbEtapes));

    this.nbIngredients = 0;
    this.ingredients = [0];
    this.formRecette.addControl(
      'ingredientNom' + this.nbIngredients,
      this.fb.control('', Validators.required)
    );
    this.formRecette.addControl(
      'ingredientQuantite' + this.nbIngredients,
      this.fb.control('', [Validators.required, FormRecetteComponent.positive])
    );
    this.formRecette.addControl(
      'ingredientUnite' + this.nbIngredients,
      this.fb.control('', Validators.required)
    );
    this.ingredientsArray.push(new Ingredient());
  }

  static positive(control: FormControl): { [key: string]: any } {
    console.log(control.value);
    if (Number(control.value) < 0) {
      return { positive: true };
    } else {
      return null;
    }
  }

  public onFileChanged(event) {
    this.selectedFile = event.target.files[0];

    //validation du format et de la taille d'image
    if (
      event.target.files[0].type != 'image/jpeg' ||
      event.target.files[0].size > 1048575
    ) {
      this.imageCtrl.setErrors({ invalid: true });
    } else {
      this.imageCtrl.setErrors(null);
    }

    // Below part is used to display the selected image
    let reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]);
    reader.onload = (event2) => {
      this.imgURL = reader.result;
    };
  }

  public save() {
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

    this.recette.dateCreation = new Date();

    this.recetteService
      .create(this.recette, this.selectedFile)
      .subscribe((res) => {
        console.log('ressss : ');
        console.log(res.id);

        this.router.navigate(['mesrecettes']);
      });
  }

  public ajouterEtape() {
    this.nbEtapes++;
    this.etapesRecette.push(new EtapeRecette('', this.nbEtapes));
    this.formRecette.addControl(
      'etapeRecette' + this.nbEtapes,
      this.fb.control('')
    );
    this.etapes.push(this.nbEtapes);
  }

  public supprimerEtape() {
    if (this.nbEtapes > 0) {
      this.etapesRecette.pop();
      this.formRecette.removeControl('etapeRecette' + this.nbEtapes);
      this.nbEtapes--;
      this.etapes.pop();
    }
  }

  public ajouterIngredient() {
    this.nbIngredients++;
    this.ingredientsArray.push(new Ingredient(''));
    this.formRecette.addControl(
      'ingredientNom' + this.nbIngredients,
      this.fb.control('', Validators.required)
    );
    this.formRecette.addControl(
      'ingredientQuantite' + this.nbIngredients,
      this.fb.control('', [Validators.required, FormRecetteComponent.positive])
    );
    this.formRecette.addControl(
      'ingredientUnite' + this.nbIngredients,
      this.fb.control('', Validators.required)
    );
    this.ingredients.push(this.nbIngredients);
  }

  public supprimerIngredient() {
    if (this.nbIngredients > 0) {
      this.ingredientsArray.pop();
      this.formRecette.removeControl('ingredientNom' + this.nbIngredients);
      this.formRecette.removeControl('ingredientQuantite' + this.nbIngredients);
      this.formRecette.removeControl('ingredientUnite' + this.nbIngredients);
      this.nbIngredients--;
      this.ingredients.pop();
    }
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

  get imageCtrl(): FormControl {
    return this._imageCtrl;
  }

  set imageCtrl(value: FormControl) {
    this._imageCtrl = value;
  }
}
