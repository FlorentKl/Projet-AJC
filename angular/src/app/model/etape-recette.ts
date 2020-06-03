import {ImageModel} from './image-model';

export class EtapeRecette {
  constructor(private _texte?: string,
              private _numEtape?: number,
              private _idRecette?: number,
              private _picture?: ImageModel){
  }

  get texte(): string {
    return this._texte;
  }

  set texte(value: string) {
    this._texte = value;
  }

  get numEtape(): number {
    return this._numEtape;
  }

  set numEtape(value: number) {
    this._numEtape = value;
  }

  get idRecette(): number {
    return this._idRecette;
  }

  set idRecette(value: number) {
    this._idRecette = value;
  }

  get picture(): ImageModel {
    return this._picture;
  }

  set picture(value: ImageModel) {
    this._picture = value;
  }
}
