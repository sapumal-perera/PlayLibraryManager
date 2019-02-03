import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteDvdComponent } from './delete-dvd.component';

describe('DeleteDvdComponent', () => {
  let component: DeleteDvdComponent;
  let fixture: ComponentFixture<DeleteDvdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeleteDvdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteDvdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
