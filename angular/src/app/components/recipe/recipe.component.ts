import { Component, OnInit } from '@angular/core';
import { Recette } from '../../model/recette';
import { RecetteService } from '../../services/recette.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Cout } from '../../model/cout.enum';
import { Difficulte } from '../../model/difficulte.enum';
import { Unite } from '../../model/unite.enum';
import { EtapeRecette } from '../../model/etape-recette';
import { CommentaireService } from 'src/app/services/commentaire.service';
import { Commentaire } from 'src/app/model/commentaire';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.css'],
})
export class RecipeComponent implements OnInit {
  private _recette: Recette = new Recette();
  private _coutString: string;
  private _difficulteString: string;
  private _id: number;
  private _erreur: boolean = false;
  private _commentaires: any;
  private _newCommentaire: Commentaire;
  private _newCommentaireAuteur: string;
  private _auteursCom: Array<string> = new Array();

  private _formCommentaire: FormGroup;
  private _noteCtrl: FormControl;
  private _texteCtrl: FormControl;

  constructor(
    private fb : FormBuilder,
    private activatedRoute: ActivatedRoute,
    private recetteService: RecetteService,
    private commService: CommentaireService,
    private router: Router
  ) {
    this._noteCtrl = fb.control('', Validators.required);
    this._texteCtrl = fb.control('', Validators.required);
    this._formCommentaire = fb.group({
      note: this._noteCtrl ,
      texte: this._texteCtrl
    });
  }

  ngOnInit(): void {
    this.initRecette();
    this.newCommentaire = new Commentaire();
    this.newCommentaire.note = 0;
    this.commService.finAllFromRecette(this.id).subscribe(
      (res) => {
        this.commentaires = res;
      },
      (err) => {
        console.log(err);
      }
    );
  }

  private initRecette() {
    this.erreur = false;
    this.activatedRoute.params.subscribe((params) => {
      if (params.id) {
        this._id = params.id;
        this.recetteService.findById(this._id).subscribe((data) => {
          this._recette = data;
          this._coutString = Cout[data.cout];
          this.difficulteString = Difficulte[data.difficulte];
          for (let ing of data.ingredients) {
            ing.unite = Unite[ing.unite];
          }
          data.etapes.sort(function (a, b) {
            return a.numEtape - b.numEtape;
          });
        });
      }
    });
  }

  public envoieCommentaire() {
    this.commentaireAuteur = this.login;
    console.log(this.newCommentaire.note);
    this.commService
      .create(this.commentaireAuteur, this.id, this.newCommentaire)
      .subscribe(
        (res) => {
          window.location.reload();
        },
        (err) => {
          console.log(err);
        }
      );
  }

  public disable(): boolean {
    return !this.formCommentaire.valid || !this.noteCtrl.dirty;
  }

  public get login() {
    return sessionStorage.getItem('login');
  }

  get difficulteString(): string {
    return this._difficulteString;
  }

  set difficulteString(value: string) {
    this._difficulteString = value;
  }

  get coutString(): string {
    return this._coutString;
  }

  set coutString(value: string) {
    this._coutString = value;
  }

  get recette(): Recette {
    return this._recette;
  }

  set recette(value: Recette) {
    this._recette = value;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get erreur(): boolean {
    return this._erreur;
  }

  set erreur(value: boolean) {
    this._erreur = value;
  }

  public get newCommentaire(): Commentaire {
    return this._newCommentaire;
  }

  public set newCommentaire(v: Commentaire) {
    this._newCommentaire = v;
  }

  public get commentaireAuteur(): string {
    return this._newCommentaireAuteur;
  }

  public set commentaireAuteur(v: string) {
    this._newCommentaireAuteur = v;
  }

  public get commentaires(): any {
    return this._commentaires;
  }

  public set commentaires(v: any) {
    this._commentaires = v;
  }


  get formCommentaire(): FormGroup {
    return this._formCommentaire;
  }

  set formCommentaire(value: FormGroup) {
    this._formCommentaire = value;
  }

  get noteCtrl(): FormControl {
    return this._noteCtrl;
  }

  set noteCtrl(value: FormControl) {
    this._noteCtrl = value;
  }

  get texteCtrl(): FormControl {
    return this._texteCtrl;
  }

  set texteCtrl(value: FormControl) {
    this._texteCtrl = value;
  }


  get auteursCom(): Array<string> {
    return this._auteursCom;
  }

  set auteursCom(value: Array<string>) {
    this._auteursCom = value;
  }
}
