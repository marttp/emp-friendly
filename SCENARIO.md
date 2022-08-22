# List of Scenario/Journey

EmpFriendly concept - do something that can improve employee life and give opportunities unrelated business role

## Declare Keywords

- **IC Point**: Internal Corporate Point
- **Employee**: People who generally work for the company
- **Driver**: Employee who assigned to work for transport/delivery purpose
- **Journey**: Transport people service purpose
- **Deliver**: Employee request driver to buy something
- **Yummy:** Method to rating restaurant
- **IC Buy**: Buying internal products function
- **Shipment**: Internal buying => Driver Received => Send to Employee Address

## Employee

- [x] Get own transaction history
- [x] Pay IC Point to restaurant partner
- ~~Pay IC Point to internal shop~~
- [ ] Use Journey
- ~~**[Journey]** Watch driver location in order by point of time (In real-time with SSE)~~ (currently support only jedis (sync)))
- [x] **[Journey]** Watch driver history by referenceId
- [ ] Use Deliver
- [ ] Use Yummy [Send rating / Get rating]
- ~~Use IC Buy~~

## Driver

- [x] Take the transport request **[Journey]**
- [x] Take the delivery request **[Deliver]**

## Restaurant Partner

- ~~[ ] CRUD Menu~~
- [x] Generate QR Code to IC Point Payment
- [x] Withdraw IC Point to Cash (can extend to transfer method) [with only deduct endpoint]

## Admin/Officer

- [x] Topup IC Point
- [x] Get history of personal top-up (Immutable)
- ~~Get marketplace order history (Immutable)~~
- [x] CRD - Restaurant Partner
- [x] CRU - Employee
- [x] Promote employee to driver (Use Edit method)
- ~~Watch driver location in order by point of time (In real-time with SSE)~~ (currently support only jedis (sync))
- [x] **[Journey]** Watch driver history by referenceId
- ~~Add Internal products and declare IC Point (CRUD Product)~~

## System

- [x] Send notification [Skeleton Message]
- [x] Generate Invoice [Skeleton Message]
- ~~Assign internal buying to Deliver **[only Shipment purpose]**~~
