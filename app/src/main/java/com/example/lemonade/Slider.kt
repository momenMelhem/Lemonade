package com.example.lemonade

import androidx.compose.material.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.utils.AppDataBase
import com.example.lemonade.utils.Juice
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Slider() {

    var listOfJuice = listOf<LemonData>(
        LemonData(label = R.string.strawberry, image = R.drawable.strawb),
        LemonData(label =R.string.watermelon, image = R.drawable.pngegg__1_),
        LemonData(label = R.string.orange, image = R.drawable.orangejuice),
        LemonData(label = R.string.mango, image = R.drawable.mango)
    )
    val context = LocalContext.current
    val juiceDao by remember { mutableStateOf(AppDataBase.getDatabaseInstance(context).juiceDao()) }

    var getJuice = remember{ mutableStateOf(juiceDao.getAll())}

    val pagerState = rememberPagerState(pageCount = { listOfJuice.size })
    var juiceList =

        mutableListOf(
            R.string.strawberry_juice,
            R.string.watermelon_juice,
            R.string.orange_juice,
            R.string.mango_juice
        )

    Box (
        modifier = Modifier
            .fillMaxSize()
    ){

        Column (
            modifier = Modifier.fillMaxSize())
        {

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .weight(1f),
                verticalAlignment = Alignment.Top

            ) { index ->

                Card (
                    modifier = Modifier,
                    border = BorderStroke(1.dp, Color.LightGray),
                    shape = RoundedCornerShape(12.dp),
                    elevation = 2.dp

                ) {

                    Column {

                        Lemon(listOfJuice[index]) {
                        }
                        Row (
                            Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.End
                        ){

                            Button(
                                shape = RoundedCornerShape(25.dp),

                                onClick = {

                                    //val juiceDao = getDatabaseInstance(context).juiceDao()
                                    juiceDao.insertJuice(Juice(juiceList[index], 0))
                                    getJuice.value = juiceDao.getAll()
                                    //  getJuice.add(juiceDao.getAll()[index].juiceName!!)

                                })
                            {
                                Icon(imageVector = Icons.Default.Add, contentDescription = "")
                                Text(text = "Add Juice",
                                    textAlign = TextAlign.Center,
                                    color = Color.White

                                )
                            }
                        }

                    }

                }

            }

            LazyColumn(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(start = 12.dp, bottom = 50.dp, top = 14.dp, end = 12.dp)
                    .fillMaxWidth()
                    .weight(1f)

            ) {
                item {

                    for (i in 0 until getJuice.value.size) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                            border = BorderStroke(1.dp, Color.LightGray),
                            shape = RoundedCornerShape(12.dp),
                            elevation = 2.dp
                        )
                        {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ){
                                Text(
                                    text = stringResource(getJuice.value[i].juiceName?:0),
                                    modifier = Modifier.padding(16.dp),
                                    fontSize = 20.sp,

                                    )
                                IconButton(
                                    onClick = {

                                        juiceDao.deleteJuice(Juice(getJuice.value[i].juiceName,getJuice.value[i].id))
                                        getJuice.value = juiceDao.getAll()

                                    },
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .padding(
                                                start = 8.dp,
                                                end = 2.dp,
                                                top = 3.dp,
                                                bottom = 2.dp
                                            )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}