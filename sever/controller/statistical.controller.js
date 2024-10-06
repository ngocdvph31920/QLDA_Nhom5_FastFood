const { response } = require('express');
const myMdO = require('../model/oders');

exports.getTotalByDate = async (req, res) => {
    try {
        const totalDelivered = await myMdO.oderModel.aggregate([
            {
                $match: { status: "delivered" }
            },
            {
                $group: {
                    _id: {
                        day: { $dayOfMonth: "$oderDate" }, // Thống kê theo ngày
                        month: { $month: "$oderDate" }, // Thống kê theo tháng
                        year: { $year: "$oderDate" } // Thống kê theo năm
                    },
                    total: { $sum: "$total" }
                }
            },
            {
                $sort: { "_id.year": 1, "_id.month": 1, "_id.day": 1 } // Sắp xếp theo năm, tháng, ngày
            }
        ]);

        totalDelivered.forEach(item => {
            console.log(`Ngày ${item._id.day}/${item._id.month}/${item._id.year} : ${item.total}`);
        });

        // res.render("../views/statistical/statistical.ejs", { totalDelivered: totalDelivered });
    } catch (error) {
        console.log("Đã xảy ra lỗi : " + error);
    }
}

exports.mostBoughtProduct = async (req,res) => {
    try {
        const mostBoughtProduct = await myMdO.oderModel.aggregate([
            {
                $match:{status:"delivered"}
            },
            {
                $unwind:"$items"
            },{
                $group:{
                    _id:"$items.productname",
                    totalQuantity:{$sum:"$items.quantity"}
                }
            },{
                $sort:{totalQuantity : -1}
            },{
                $limit:10
            }
        ]);
        console.log(mostBoughtProduct);
        res.render('../views/statistical/statistical.ejs',{mostBoughtProduct:mostBoughtProduct});
    } catch (error) {
        console.log("Đã có lỗi : "+error);
    }
}
