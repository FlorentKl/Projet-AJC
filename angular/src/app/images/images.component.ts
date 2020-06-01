import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-images',
  templateUrl: './images.component.html',
  styleUrls: ['./images.component.css'],
})
export class ImagesComponent implements OnInit {
  constructor(private httpClient: HttpClient) {}

  title = 'ImageUploaderFrontEnd';

  public selectedFile;
  public event1;
  imgURL: any;
  // receivedImageData: any;
  // base64Data: any;
  // convertedImage: any;

  public onFileChanged(event) {
    console.log(event);
    this.selectedFile = event.target.files[0];

    // Below part is used to display the selected image
    let reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]);
    reader.onload = (event2) => {
      this.imgURL = reader.result;
    };
  }

  // This part is for uploading
  onUpload() {
    console.log('onUpload pressé');
    console.log(this.selectedFile);
    const uploadData = new FormData();
    uploadData.append('image', this.selectedFile, this.selectedFile.name);

    this.httpClient
      .post('http://localhost:8080/web/rest/image/upload', uploadData)
      .subscribe(
        (res) => {
          console.log(res);
        },
        (err) => {
          console.log(err);
        }
      );
    // .subscribe(
    //   (res) => {
    //     console.log(res);
    //     this.receivedImageData = res;
    //     this.base64Data = this.receivedImageData.pic;
    //     this.convertedImage = 'data:image/jpeg;base64,' + this.base64Data;
    //   },
    //   (err) => console.log('Error Occured duringng saving: ' + err)
    // );
  }

  ngOnInit(): void {}
}
