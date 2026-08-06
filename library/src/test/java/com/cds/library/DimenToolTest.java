package com.cds.library;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DimenToolTest {

    @Test
    public void scaleDp_roundsHalfUp() {
        assertEquals(200, DimenTool.scaleDp(200, 360, 360));
        assertEquals(267, DimenTool.scaleDp(200, 480, 360));
        assertEquals(133, DimenTool.scaleDp(200, 240, 360));
        assertEquals(800, DimenTool.scaleDp(200, 1440, 360));
    }

    @Test
    public void scaleDp_bucketMatchesFolderName() {
        // Previously sw380 used 384 and sw760 used 768; folders now match the factor.
        assertEquals(211, DimenTool.scaleDp(200, 380, 360));
        assertEquals(422, DimenTool.scaleDp(200, 760, 360));
    }

    @Test
    public void shouldScale_onlyDpDip() {
        assertTrue(DimenTool.shouldScale("dp"));
        assertTrue(DimenTool.shouldScale("DIP"));
        assertFalse(DimenTool.shouldScale("sp"));
        assertFalse(DimenTool.shouldScale("px"));
        assertFalse(DimenTool.shouldScale("pt"));
        assertFalse(DimenTool.shouldScale(null));
    }

    @Test
    public void appendScaledLine_scalesDpKeepsSpAndPx() {
        Map<Integer, StringBuilder> outputs = new LinkedHashMap<Integer, StringBuilder>();
        outputs.put(480, new StringBuilder());

        DimenTool.appendScaledLine(
                "    <dimen name=\"widget_size_200\">200dp</dimen>", 360, outputs);
        DimenTool.appendScaledLine(
                "    <dimen name=\"font_size_16\">16sp</dimen>", 360, outputs);
        DimenTool.appendScaledLine(
                "    <dimen name=\"widget_min_size_1\">1px</dimen>", 360, outputs);

        String result = outputs.get(480).toString();
        assertTrue(result.contains("widget_size_200\">267dp</dimen>"));
        assertTrue(result.contains("font_size_16\">16sp</dimen>"));
        assertTrue(result.contains("widget_min_size_1\">1px</dimen>"));
    }

    @Test
    public void appendScaledLine_preservesNonDimenLines() {
        Map<Integer, StringBuilder> outputs = new LinkedHashMap<Integer, StringBuilder>();
        outputs.put(360, new StringBuilder());

        DimenTool.appendScaledLine("    <!-- comment -->", 360, outputs);
        assertEquals("    <!-- comment -->\n", outputs.get(360).toString());
    }

    @Test
    public void formatNumber_dropsTrailingZero() {
        assertEquals("16", DimenTool.formatNumber(16f));
        assertEquals("16.5", DimenTool.formatNumber(16.5f));
    }
}
