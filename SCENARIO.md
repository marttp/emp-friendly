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

- Get own transaction history
- Pay IC Point to restaurant partner
- Pay IC Point to internal shop
- Use Journey
- **[Journey]** Watch driver location in order by point of time (In real-time)
- Use Deliver
- Use Yummy [Send rating / Get rating]
- Use IC Buy

## Driver

- Take the transport request **[Journey]**
- Take the delivery request **[Deliver]**

## Restaurant Partner

- CRUD Menu
- Generate QR Code to IC Point Payment
- Withdraw IC Point to Cash (can extend to transfer method)

## Admin/Officer

- Topup IC Point
- Get history of personal top-up (Immutable)
- Get marketplace order history (Immutable)
- CRUD - Restaurant Partner
- CRU - Employee (able to mark driver)
- Promote employee to driver
- Watch driver location in order by point of time (In real-time)
- CRUD coupon in topup scenario (campaign)
- Add Internal products and declare IC Point

## System

- Send notification [Skeleton Message ✅]
- Generate Invoice [Skeleton Message ✅]
- Assign internal buying to Deliver **[only Shipment purpose]**
