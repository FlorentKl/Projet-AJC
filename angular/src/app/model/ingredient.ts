export class Ingredient {
  constructor(private _nom?: string, private _quantite?: number, private _mesure_solide?: string, private _mesure_liquide?: string){
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



  get mesure_solide(): string {
    return this._mesure_solide;
  }

  set mesure_solide(value: string) {
    this._mesure_solide = value;
  }

  get mesure_liquide(): string {
    return this._mesure_liquide;
  }

  set mesure_liquide(value: string) {
    this._mesure_liquide = value;
  }
}
