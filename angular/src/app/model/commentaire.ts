export class Commentaire {
  constructor(private _texte?: string, private _note?: number) {}

  public get note(): number {
    return this._note;
  }

  public set note(v: number) {
    this._note = v;
  }

  get texte(): string {
    return this._texte;
  }

  set texte(value: string) {
    this._texte = value;
  }
}
