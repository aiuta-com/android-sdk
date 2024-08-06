#!/bin/bash

# Define the path to the gradle.properties file
GRADLE_PROPERTIES_FILE="gradle.properties"
VERSION_KEY="POM_VERSION"

# Function to get the current value of a property
get_property() {
    local key="$1"
    local file="$2"
    local value=$(grep "^$key=" "$file" | cut -d'=' -f2)
    echo "$value"
}

# Function to set the value of a property
set_property() {
    local key="$1"
    local value="$2"
    local file="$3"
    sed -i -e "s/^$key=.*/$key=$value/" "$file"
}

# Check if the required arguments are provided
if [ "$#" -ne 1 ]; then
    echo "Usage: $0 <new_value>"
    exit 1
fi

# Get the current value of the property
current_value=$(get_property "$VERSION_KEY" "$GRADLE_PROPERTIES_FILE")

# Set the new value of the property
set_property "$VERSION_KEY" "$1" "$GRADLE_PROPERTIES_FILE"

echo "Updated '$VERSION_KEY' from '$current_value' to '$1' in $GRADLE_PROPERTIES_FILE."
