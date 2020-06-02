import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormIngredientRecetteComponent } from './form-ingredient-recette.component';

describe('FormIngredientRecetteComponent', () => {
  let component: FormIngredientRecetteComponent;
  let fixture: ComponentFixture<FormIngredientRecetteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormIngredientRecetteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormIngredientRecetteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
