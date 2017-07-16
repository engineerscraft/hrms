import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css'],
  providers: [EmployeeService]
})
export class EmployeeDetailsComponent implements OnInit {

  employeeId = localStorage.getItem("userName");
  employeeInfo;

  constructor(private employeeService: EmployeeService) {
    
   }

  ngOnInit() {
    this.employeeService.read(this.employeeId)
    .subscribe(data => {
      this.employeeInfo = data;
      console.log("Employee Service Read Response : "+JSON.stringify(this.employeeInfo));
    });
    console.log(this.employeeId+"Employee Info Log :: "+JSON.stringify(this.employeeInfo));
  }

}
