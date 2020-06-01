import {Couts} from './couts.enum';
import {Difficulte} from './difficulte.enum';
import {ImageModel} from './image-model';
import {Utilisateur} from './utilisateur';

export class Recette {
  constructor(private _nom?: string, private _id?: number, private _nbPersonnes?: number,
              private _temps?: number, private _couts?: Couts, private _difficulte?: Difficulte,
              private _picture?: ImageModel, private _dateCreation?: Date, private _auteur?: Utilisateur) {
  }


  get nom(): string {
    return this._nom;
  }

  set nom(value: string) {
    this._nom = value;
  }

  get nbPersonnes(): number {
    return this._nbPersonnes;
  }

  set nbPersonnes(value: number) {
    this._nbPersonnes = value;
  }

  get temps(): number {
    return this._temps;
  }

  set temps(value: number) {
    this._temps = value;
  }

  get couts(): Couts {
    return this._couts;
  }

  set couts(value: Couts) {
    this._couts = value;
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

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
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
}
