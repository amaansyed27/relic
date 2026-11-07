package com.amaansyed.relic.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amaansyed.relic.RelicSession

private val background = Color(0xFF0D151D)
private val panel = Color(0xFF15222B)
private val cyan = Color(0xFFA3DCE8)
private val amber = Color(0xFFF0C579)
private val text = Color(0xFFE4E9E4)
private val dim = Color(0xFF8DABB6)
private val grid = Color(0xFF263B47)

@Composable
fun RelicApp() {
    var coreActive by rememberSaveable { mutableStateOf(false) }
    val session = RelicSession(coreActive)

    MaterialTheme {
        Surface(color = background) {
            Box(Modifier.fillMaxSize()) {
                Canvas(Modifier.fillMaxSize()) {
                    val step = 36.dp.toPx()
                    var x = 0f
                    while (x < size.width) {
                        drawLine(grid.copy(alpha = 0.24f), start = androidx.compose.ui.geometry.Offset(x, 0f),
                            end = androidx.compose.ui.geometry.Offset(x, size.height))
                        x += step
                    }
                    var y = 0f
                    while (y < size.height) {
                        drawLine(grid.copy(alpha = 0.24f), start = androidx.compose.ui.geometry.Offset(0f, y),
                            end = androidx.compose.ui.geometry.Offset(size.width, y))
                        y += step
                    }
                }

                Column(
                    modifier = Modifier.fillMaxSize().safeDrawingPadding().padding(24.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically) {
                        Text("R / 01", color = amber, fontSize = 17.sp,
                            fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
                        Text("EXPERIMENTAL INTERFACE", color = dim, fontSize = 10.sp,
                            letterSpacing = 1.sp, fontFamily = FontFamily.Monospace)
                    }

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(Modifier.size(190.dp), contentAlignment = Alignment.Center) {
                            Canvas(Modifier.fillMaxSize()) {
                                val radius = size.minDimension * 0.42f
                                drawCircle(cyan.copy(alpha = if (session.coreActive) 0.8f else 0.32f),
                                    radius = radius, style = Stroke(width = 1.5.dp.toPx()))
                                drawCircle(amber.copy(alpha = 0.65f), radius = radius * 0.84f,
                                    style = Stroke(width = 1.dp.toPx()))
                                drawCircle(cyan.copy(alpha = 0.3f), radius = radius * 0.52f,
                                    style = Stroke(width = 1.dp.toPx()))
                            }
                            Text("R", color = if (session.coreActive) amber else dim, fontSize = 75.sp,
                                fontWeight = FontWeight.Light, fontFamily = FontFamily.Monospace)
                        }

                        Spacer(Modifier.height(24.dp))
                        Text(session.headline, color = text, fontSize = 23.sp,
                            fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp, textAlign = TextAlign.Center)
                        Spacer(Modifier.height(8.dp))
                        Text(session.detail, color = dim, fontSize = 13.sp,
                            fontFamily = FontFamily.Monospace, textAlign = TextAlign.Center)

                        Spacer(Modifier.height(32.dp))
                        Button(
                            onClick = { coreActive = session.toggle().coreActive },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(7.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = amber, contentColor = background)
                        ) {
                            Text(session.actionLabel, fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Bold, letterSpacing = 1.sp,
                                modifier = Modifier.padding(vertical = 8.dp))
                        }
                    }

                    Column(Modifier.fillMaxWidth().background(panel, RoundedCornerShape(8.dp))
                        .border(1.dp, grid, RoundedCornerShape(8.dp)).padding(16.dp)) {
                        Text("DIAGNOSTICS / LOCAL", color = amber, fontSize = 11.sp,
                            fontFamily = FontFamily.Monospace, letterSpacing = 1.sp)
                        Spacer(Modifier.height(16.dp))
                        StatusLine("PHONE CORE", if (coreActive) "READY" else "STANDBY")
                        Spacer(Modifier.height(9.dp))
                        StatusLine("USB LINK", session.usbStatus)
                        Spacer(Modifier.height(9.dp))
                        StatusLine("EXTERNAL BOARD", "NOT DETECTED")
                        Spacer(Modifier.height(16.dp))
                        Text("Hardware communications begin in Slice 02.", color = dim,
                            fontSize = 11.sp, fontFamily = FontFamily.Monospace)
                    }
                }
            }
        }
    }
}

@Composable
private fun StatusLine(label: String, value: String) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = dim, fontSize = 11.sp, fontFamily = FontFamily.Monospace)
        Spacer(Modifier.width(12.dp))
        Text(value, color = cyan, fontSize = 11.sp, fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Medium)
    }
}
