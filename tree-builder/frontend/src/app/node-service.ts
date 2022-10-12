import { Injectable, OnInit } from "@angular/core";
import axios from "axios";

var url = "http://localhost:8080/api/";

@Injectable()
export class NodeService implements OnInit {
  constructor() {}

  ngOnInit(): void {}

  // creation of node
  createNode = (id: any, nodeDetails: any) => {
    axios
      .post(url + "addChildNode/" + id, nodeDetails)
      .then((res: any) => {
        alert("Succesfully added!");
      })
      .catch((res: any) => {
        console.log(res.error);
      });
  };

  // fetching of nodes
  getNodesFromDb = () => {
    return axios
      .get(url + "get")
      .then((res: any) => {
        const response: any = [];
        response.push(res.data);
        return response;
      })
      .catch((res: any) => {
        console.log(res.error);
      });
  };

  // get node details form db by id
  getNodeByID = (id: any) => {
    return axios
      .get(url + "getNodeById/" + id)
      .then((res: any) => {
        return res.data;
      })
      .catch((res: any) => {
        console.log(res.error);
      });
  };

  // Update existing node
  updateNode = (id: any, nodeDetails: any) => {
    axios
      .put(url + "updateNode/" + id, nodeDetails)
      .then((res: any) => {
        alert("Succesfully updated node with id : " + id);
      })
      .catch((res: any) => {
        console.log(res.error);
      });
  };

  // deletion of node
  deleteNode = (id: any) => {
    axios.delete(url + "deleteNode/" + id).then((res: any) => {
      alert("Succesfully deleted node with id : " + id);
    });
  };
}
