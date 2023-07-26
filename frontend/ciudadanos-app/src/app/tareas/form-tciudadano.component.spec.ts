import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormTCiudadanoComponent } from './form-tciudadano.component';

describe('FormTCiudadanoComponent', () => {
  let component: FormTCiudadanoComponent;
  let fixture: ComponentFixture<FormTCiudadanoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormTCiudadanoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormTCiudadanoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
