export class Commentaire {
  constructor(private _texte?: string){}


  get texte(): string {
    return this._texte;
  }

  set texte(value: string) {
    this._texte = value;
  }
}
