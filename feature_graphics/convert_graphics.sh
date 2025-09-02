#!/bin/bash

# Converty Graphics Conversion Script
# Converts SVG graphics to PNG and JPG formats for various uses

echo "🎨 Converting Converty Feature Graphics..."
echo "========================================"

# Check if ImageMagick is installed
if ! command -v convert &> /dev/null; then
    echo "❌ ImageMagick not found. Please install it first:"
    echo "   macOS: brew install imagemagick"
    echo "   Ubuntu: sudo apt-get install imagemagick"
    echo "   Windows: Download from https://imagemagick.org/script/download.php"
    exit 1
fi

# Create output directories
mkdir -p png png/high-res png/web-optimized
mkdir -p jpg jpg/high-quality jpg/web-optimized

# List of SVG files to convert
SVG_FILES=(
    "main_feature_graphic.svg"
    "mobile_showcase_graphic.svg" 
    "currency_focus_graphic.svg"
    "social_media_square.svg"
    "play_store_feature_graphic.svg"
)

echo ""
echo "📁 Converting SVG files..."

for svg_file in "${SVG_FILES[@]}"; do
    if [ -f "$svg_file" ]; then
        filename=$(basename "$svg_file" .svg)
        echo "   Processing: $svg_file"
        
        # High-resolution PNG (300 DPI equivalent)
        convert "$svg_file" -density 300 -quality 100 "png/high-res/${filename}.png"
        
        # Web-optimized PNG (150 DPI equivalent)
        convert "$svg_file" -density 150 -quality 90 "png/web-optimized/${filename}.png"
        
        # High-quality JPG
        convert "$svg_file" -density 300 -quality 95 -background white -flatten "jpg/high-quality/${filename}.jpg"
        
        # Web-optimized JPG
        convert "$svg_file" -density 150 -quality 80 -background white -flatten "jpg/web-optimized/${filename}.jpg"
        
        echo "     ✅ Converted to PNG and JPG"
    else
        echo "     ❌ File not found: $svg_file"
    fi
done

echo ""
echo "📊 Conversion Summary:"
echo "======================"
echo "High-res PNG:     png/high-res/"
echo "Web PNG:          png/web-optimized/"  
echo "High-quality JPG: jpg/high-quality/"
echo "Web JPG:          jpg/web-optimized/"

echo ""
echo "📱 Usage Recommendations:"
echo "========================="
echo "Google Play Store:    Use high-res PNG files"
echo "Social Media:         Use web-optimized PNG files"
echo "Email Marketing:      Use web-optimized JPG files"
echo "Website Headers:      Use web-optimized PNG files"
echo "Print Materials:      Use high-res PNG files"

echo ""
echo "✨ Conversion completed successfully!"
echo "   Check the png/ and jpg/ directories for converted files."

# Display file sizes
echo ""
echo "📏 File Sizes:"
echo "=============="
if [ -d "png/high-res" ]; then
    echo "High-res PNG files:"
    ls -lh png/high-res/ | grep -v total
fi

if [ -d "jpg/web-optimized" ]; then
    echo ""
    echo "Web-optimized JPG files:"
    ls -lh jpg/web-optimized/ | grep -v total
fi
