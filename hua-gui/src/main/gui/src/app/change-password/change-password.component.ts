import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  private formGroup: FormGroup;

  constructor(private router: Router, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.formGroup = this.formBuilder.group({
      currentPassword: ['', [Validators.required, Validators.minLength(3)]],
      newPassword: ['', Validators.required],
      confirmedPassword: ['', Validators.required]
    });
  }

  onCancel() {
    this.router.navigate(['home']);
  }

  onSubmit() {

  }

  isProcessingInProgress() {
    return false;
  }

}
