import { Ingredient } from './ingredient';
import { EtapeRecette } from './etape-recette';
import { Commentaire } from './commentaire';
import { Cout } from './cout.enum';
import { Difficulte } from './difficulte.enum';
import { ImageModel } from './image-model';
import { Utilisateur } from '../user/model/utilisateur';

export class Recette {
  constructor(
    private _id?: number,
    private _nom?: string,
    private _type?: string,
    private _nbPersonne?: number,
    private _temps?: number,
    private _cout?: Cout,
    private _difficulte?: Difficulte,
    private _picture?: ImageModel,
    private _dateCreation?: Date,
    private _tags?: string[],
    private _ingredients?: Ingredient[],
    private _etapes?: EtapeRecette[],
    private _commentaires?: Commentaire[],
    private _auteur?: Utilisateur
  ) {}

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get nom(): string {
    return this._nom;
  }

  set nom(value: string) {
    this._nom = value;
  }

  get nbPersonne(): number {
    return this._nbPersonne;
  }

  set nbPersonne(value: number) {
    this._nbPersonne = value;
  }

  get temps(): number {
    return this._temps;
  }

  set temps(value: number) {
    this._temps = value;
  }

  get cout(): Cout {
    return this._cout;
  }

  set cout(value: Cout) {
    this._cout = value;
  }

  get tags(): string[] {
    return this._tags;
  }

  set tags(value: string[]) {
    this._tags = value;
  }

  get ingredients(): Ingredient[] {
    return this._ingredients;
  }

  set ingredients(value: Ingredient[]) {
    this._ingredients = value;
  }

  get etapes(): EtapeRecette[] {
    return this._etapes;
  }

  set etapes(value: EtapeRecette[]) {
    this._etapes = value;
  }

  get picture(): ImageModel {
    return this._picture;
  }

  set picture(value: ImageModel) {
    this._picture = value;
  }

  get auteur(): Utilisateur {
    return this._auteur;
  }

  set auteur(value: Utilisateur) {
    this._auteur = value;
  }

  get difficulte(): Difficulte {
    return this._difficulte;
  }

  set difficulte(value: Difficulte) {
    this._difficulte = value;
  }

  get dateCreation(): Date {
    return this._dateCreation;
  }

  set dateCreation(value: Date) {
    this._dateCreation = value;
  }

  get commentaires(): Commentaire[] {
    return this._commentaires;
  }

  set commentaires(value: Commentaire[]) {
    this._commentaires = value;
  }
  get type(): string {
    return this._type;
  }

  set type(value: string) {
    this._type = value;
  }
}
