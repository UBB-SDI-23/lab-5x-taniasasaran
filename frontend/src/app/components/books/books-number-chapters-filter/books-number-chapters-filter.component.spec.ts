import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BooksNumberChaptersFilterComponent } from './books-number-chapters-filter.component';

describe('BooksNumberChaptersFilterComponent', () => {
  let component: BooksNumberChaptersFilterComponent;
  let fixture: ComponentFixture<BooksNumberChaptersFilterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BooksNumberChaptersFilterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BooksNumberChaptersFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
