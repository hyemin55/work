import axios from "axios"
import { GLOBAL_URL } from "./util"

export const getBestProducts = async () => {
    try {
      const res = await axios.get(`${GLOBAL_URL}/api/products/best`)
      console.log(res.data)
      return res.data
    } catch (err) {
      console.log(err)
      return err
    }
  }