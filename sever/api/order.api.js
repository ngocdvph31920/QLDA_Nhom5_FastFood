const myMd = require('../model/oders');
const myMdP = require('../model/product');
const msg = "";

exports.addOder = async (req, res) => {
    try {
        const {username, total, address, orderId,items } = req.body;

        if (!Array.isArray(items)) {
            console.log("Item is not an array");
        }
    
        const newItems = items.map(item => ({
            productname: item.productname,
            quantity: item.quantity,
            price: item.price,
        }));
        const newOrder = new myMd.oderModel({
            username,
            total,
            oderDate: Date.now(),
            address,
            items: newItems,
            status: "Confirm",
        });

        const saveOrder = await newOrder.save();
       
    
        console.log(saveOrder._id);
        console.log("Đơn hàng đã được đặt thành công");
        res.status(200).json({ newOrder, msg: `Đơn hàng ${saveOrder._id} đặt hàng thành công` });
    } catch (error) {
        console.log("Đã xảy ra lỗi khi tạo đơn hàng :" + error);
    }
};

exports.getAllOder = async (req, res) => {
    try {
        const oders = await myMd.oderModel.find();
        console.log("Oder : " + oders);
        res.json(oders);
    } catch (error) {
        console.log("Đã có lỗi xảy ra khi lấy danh sách oder " + error);
    }
}

exports.getOder = async (req, res) => {
    try {
        const oders = await myMd.oderModel.find({ status: "Confirm" });
        console.log("Oder : " + oders);
        res.json(oders);
    } catch (error) {
        console.log("Đã có lỗi xảy ra khi lấy danh sách oder " + error);
    }
}
exports.getPreparingGoods = async (req, res) => {
    try {
        const oders = await myMd.oderModel.find({ status: "preparing goods" });
        console.log("Oder : " + oders);
        res.json(oders);
    } catch (error) {
        console.log("Đã có lỗi xảy ra khi lấy danh sách oder " + error);
    }
}

exports.getAreDelivering = async (req, res) => {
    try {
        const status = req.params.status;
        const oders = await myMd.oderModel.find({ status: "are delivering" });
        console.log("Oder : " + oders);
        res.json(oders);
    } catch (error) {
        console.log("Đã có lỗi xảy ra khi lấy danh sách oder " + error);
    }
}
exports.getDelived = async (req, res) => {
    try {
        const oders = await myMd.oderModel.find({ status: "delivered" });
        console.log("Oder : " + oders);
        res.json(oders);
    } catch (error) {
        console.log("Đã có lỗi xảy ra khi lấy danh sách oder " + error);
    }
}

exports.mostBoughtProduct = async (req,res) => {
    try {
        const mostBoughtProduct = await myMd.oderModel.aggregate([
            {
                $match:{status:"delivered"}
            },
            {
                $unwind:"$items"
            },{
                $group:{
                    _id:"$items._id",
                    totalQuantity:{$sum:"$items.quantity"}
                }
            },{
                $sort:{totalQuantity : -1}
            },{
                $limit:10
            }
        ]);

        const products = 

        res.json(mostBoughtProduct);
    } catch (error) {
        console.log("Đã có lỗi : "+error);
    }
}