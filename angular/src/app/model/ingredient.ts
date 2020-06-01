export class Ingredient {
  constructor(private _nom?: string, private _quantite?: number, ){
  }

  get nom(): string {
    return this._nom;
  }

  set nom(value: string) {
    this._nom = value;
  }


  get quantite(): number {
    return this._quantite;
  }

  set quantite(value: number) {
    this._quantite = value;
  }
}
