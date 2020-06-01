import {Ingredient} from './ingredient';
import {EtapeRecette} from './etape-recette';

export class Recette {
  get type(): string {
    return this._type;
  }

  set type(value: string) {
    this._type = value;
  }
  constructor(private _id?: number,
               private _nom?: string,
               private _type?: string,
               private _nbPersonne?: number,
               private _temps?: number,
               private _cost?: string,
               private _difficulte?: string,
               private _tags?: string[],
               private _ingredients?: Ingredient[],
               private _etapes?: EtapeRecette[],
               private _auteur?: string
               ) {
  }


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

  get cost(): string {
    return this._cost;
  }

  set cost(value: string) {
    this._cost = value;
  }

  get difficulte(): string {
    return this._difficulte;
  }

  set difficulte(value: string) {
    this._difficulte = value;
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

  get auteur(): string {
    return this._auteur;
  }

  set auteur(value: string) {
    this._auteur = value;
  }
}
