/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';
import { ReactiveFormsModule} from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';

import { ItemSpecificationsComponent } from './item-specifications.component';

describe('ItemSpecificationsComponent', () => {
  let component: ItemSpecificationsComponent;
  let fixture: ComponentFixture<ItemSpecificationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItemSpecificationsComponent ],
      imports:[ 
        ReactiveFormsModule,RouterTestingModule
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItemSpecificationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
   });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
