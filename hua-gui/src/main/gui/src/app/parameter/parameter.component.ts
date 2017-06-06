import { Component, OnInit, Inject, ViewChild, ElementRef } from '@angular/core';
import { ParameterService } from '../parameter.service';

@Component({
  selector: 'app-parameter',
  templateUrl: './parameter.component.html',
  styleUrls: ['./parameter.component.css'],
  host: {
    '(document:click)': 'onClick($event)',
  }
})
export class ParameterComponent implements OnInit {

  @ViewChild("inputMaterial") input: ElementRef;
  @ViewChild("table") table: ElementRef;

  private mouseLocationX = 0;
  private mouseLocationY = 0;

  private parameters;
  private processingInProgress = false;
  private showContextMenu = false;
  private mouseLocation : {left:number,top:number} = {'left':0, 'top':0};
  private showParameterModifyPage = false;
  private showParameterViewPage = true;

  private parameter = {key: "", value: "", clobValue: "", description: ""};

  constructor(private parameterService : ParameterService) { 
  }

  ngOnInit() {
    this.processingInProgress = true;
    this.parameterService.getParameters()
      .subscribe(
        res => {
          this.parameters = res;
        },
        err => {
          this.processingInProgress = false;
        },
        () => {
          this.processingInProgress = false;
        }
      );
   }

  onRightClick(event: MouseEvent, key: string, value: string, clobValue: string, description: string) {
    this.showContextMenu = true;
    this.mouseLocation.left = event.clientX;
    this.mouseLocation.top = event.clientY;
    event.stopPropagation();
    this.parameter.key = key;
    this.parameter.value = value;
    this.parameter.clobValue = clobValue;
    this.parameter.description = description;
    return false;
  }

  onClick(event : Event) {
    this.showContextMenu = false;
  }

  filterParameters() {
    var filter, row, cell, i;
    filter = this.input.nativeElement.value.toUpperCase();
    row = this.table.nativeElement.getElementsByClassName("row");

    for(i = 0; i < row.length; i++) {
      if(i>0) {
        cell = row[i].getElementsByClassName("cell")[0]; 
        if(cell.innerHTML.toUpperCase().indexOf(filter) > -1) {
          row[i].style.display = "";
        }
        else {
          row[i].style.display = "none";
        }
      }
    }
  }

  getContextMenuCss() {
    return {
      'position':'fixed',
      'display':this.showContextMenu ? 'block':'none',
      'left':this.mouseLocation.left + 'px',
      'top':this.mouseLocation.top + 'px'
    }
  }

  onModifyClick() {
    this.showParameterModifyPage = true;
    this.showParameterViewPage = false;
    window.scrollTo(0,0);
  }

  onBackClick() {
    this.showParameterModifyPage = false;
    this.showParameterViewPage = true;
    window.scrollTo(0,0);
  }
}
