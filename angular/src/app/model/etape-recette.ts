export class EtapeRecette {
  constructor(private _texte?: string, private _numEtape?: number){
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
}
