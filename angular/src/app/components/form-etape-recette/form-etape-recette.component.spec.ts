import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormEtapeRecetteComponent } from './form-etape-recette.component';

describe('FormEtapeRecetteComponent', () => {
  let component: FormEtapeRecetteComponent;
  let fixture: ComponentFixture<FormEtapeRecetteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormEtapeRecetteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormEtapeRecetteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
