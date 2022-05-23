API Requests
1 Get
- localhost:8080/parking/empty  - response: { 0 } - count of empty parking spot
- localhost:8080/parking/all    - response: { 0 } - count of all parking spots
- localhost:8080//client/{id}   - response: {
                                                "name": "Maria",
                                                "discount": 10,
                                                "id": 2
                                            }
- localhost:8080/car/{id}       - response: {
                                                    "id": "BR564L",
                                                    "model": "BMW",
                                                    "client": {
                                                        "name": "Maria",
                                                        "discount": 10,
                                                        "id": 2
                                                    },
                                                    "reservation": {
                                                        "startTime": "10:45:00",
                                                        "price": 5000,
                                                        "id": 2
                                                    }
                                                } - get car/cars info by client id
- localhost:8080/get            - response: {
                                                    "id": 1,
                                                    "car": {
                                                        "id": "BTT65H",
                                                        "model": "Hyundai",
                                                        "client": {
                                                            "name": "Tommy",
                                                            "discount": 5,
                                                            "id": 4
                                                        },
                                                        "reservation": {
                                                            "startTime": "15:03:42",
                                                            "price": 100,
                                                            "id": 1
                                                        }
                                                    },
                                                    "available": "false",
                                                    "overrun": "false"
                                                } ... - get full info about all parking spots
2 Post
- localhost:8080/post           - body: {
                                                "id": 10,
                                                "car": {
                                                    "id": "IIU91G",
                                                    "model": "BMW",
                                                    "client": {
                                                        "id": 5,
                                                        "name": "Harry",
                                                        "discount": 0
                                                    },
                                                    "reservation": {
                                                        "id": 11,
                                                        "startTime": "05:58:13",
                                                        "price": 100
                                                    }
                                                },
                                                "available": "false",
                                                "overrun": "true"
                                        }
                                - response: {
                                                    "id": 10,
                                                    "car": {
                                                        "id": "IIU91G",
                                                        "model": "BMW",
                                                        "client": {
                                                            "id": 5,
                                                            "name": "Harry",
                                                            "discount": 0
                                                        },
                                                        "reservation": {
                                                            "id": 11,
                                                            "startTime": "05:58:13",
                                                            "price": 100
                                                        }
                                                    },
                                                    "available": "false",
                                                    "overrun": "true"
                                            } - post new info about parking spot
3 Put
- localhost:8080/put            - body: {
                                                "id": 10,
                                                "car": {
                                                    "id": "IIU91G",
                                                    "model": "BMW",
                                                    "client": {
                                                        "id": 5,
                                                        "name": "Harry",
                                                        "discount": 0
                                                    },
                                                    "reservation": {
                                                        "id": 11,
                                                        "startTime": "05:58:13",
                                                        "price": 100
                                                    }
                                                },
                                                "available": "false",
                                                "overrun": "true"
                                        }
                                - response: {
                                                    "id": 10,
                                                    "car": {
                                                        "id": "IIU91G",
                                                        "model": "BMW",
                                                        "client": {
                                                            "id": 5,
                                                            "name": "Harry",
                                                            "discount": 0
                                                        },
                                                        "reservation": {
                                                            "id": 11,
                                                            "startTime": "05:58:13",
                                                            "price": 100
                                                        }
                                                    },
                                                    "available": "false",
                                                    "overrun": "true"
                                            } - update info about parking spot
4 Delete
- localhost:8080/delete/{id}    - response: "success"/"failure" - delete parking spot by id
